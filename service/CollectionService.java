package edu.ncsu.csc440.poketrader.service;

import edu.ncsu.csc440.poketrader.dto.CollectionDto;
import edu.ncsu.csc440.poketrader.dto.CardDto;
import edu.ncsu.csc440.poketrader.dto.UserCardDto;
import edu.ncsu.csc440.poketrader.entity.Card;
import edu.ncsu.csc440.poketrader.entity.CardCollection;
import edu.ncsu.csc440.poketrader.entity.UserCard;
import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.repository.CardCollectionRepository;
import edu.ncsu.csc440.poketrader.repository.CardRepository;
import edu.ncsu.csc440.poketrader.repository.UserCardRepository;
import edu.ncsu.csc440.poketrader.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Carter
 */
@Service
public class CollectionService {

    private final CardCollectionRepository collectionRepository;
    private final UserCardRepository userCardRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CollectionService(CardCollectionRepository collectionRepository,
                             UserCardRepository userCardRepository,
                             CardRepository cardRepository,
                             UserRepository userRepository) {
        this.collectionRepository = collectionRepository;
        this.userCardRepository = userCardRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public List<CollectionDto> getUserCollections(Long userId) {
        List<CardCollection> collections = collectionRepository.findByUser_UserId(userId);
        List<CollectionDto> dtos = new ArrayList<>();
        for (CardCollection collection : collections) {
            dtos.add(new CollectionDto(
                    collection.getCollectionId(),
                    collection.getUser().getUserId(),
                    collection.getName(),
                    collection.getDescription()
            ));
        }
        return dtos;
    }

    public CollectionDto createCollection(Long userId, CollectionDto dto) {
        if (userId == null || dto == null || dto.getName() == null) {
            throw new IllegalArgumentException("Missing required collection fields");
        }
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }

        CardCollection collection = new CardCollection();
        User user = userRepository.findById(userId).orElse(null);
        collection.setUser(user);
        collection.setName(dto.getName());
        collection.setDescription(dto.getDescription());
        CardCollection saved = collectionRepository.save(collection);

        return new CollectionDto(
                saved.getCollectionId(),
                saved.getUser().getUserId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    public CollectionDto getCollectionById(Long collectionId, Long ownerId) {
        CardCollection collection = collectionRepository.findById(collectionId).orElse(null);
        if (collection == null) {
            throw new IllegalArgumentException("Collection not found");
        }

        if (!collection.getUser().getUserId().equals(ownerId)) {
             throw new IllegalArgumentException("Collection not owned by user");
        }

        return new CollectionDto(
                collection.getCollectionId(),
                collection.getUser().getUserId(),
                collection.getName(),
                collection.getDescription()
        );
    }

    public CollectionDto updateCollection(Long collectionId, CollectionDto dto) {
        CardCollection collection = collectionRepository.findById(collectionId).orElse(null);
        if (collection == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        collection.setName(dto.getName());
        collection.setDescription(dto.getDescription());
        CardCollection saved = collectionRepository.save(collection);
        return new CollectionDto(
                saved.getCollectionId(),
                saved.getUser().getUserId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    public void deleteCollection(Long collectionId) {
        List<UserCard> userCards = userCardRepository.findByCollection_CollectionId(collectionId);
        for (UserCard userCard : userCards) {
            userCardRepository.delete(userCard);
        }
        collectionRepository.deleteById(collectionId);
    }

    public List<UserCardDto> getCardsInCollection(Long collectionId) {
        List<UserCard> userCards = userCardRepository.findByCollection_CollectionId(collectionId);
        List<UserCardDto> dtos = new ArrayList<>();

        for (UserCard userCard : userCards) {
            Card card = cardRepository.findById(userCard.getCard().getCardId()).orElse(null);
            CardDto cardDto = null;
            if (card != null) {
                cardDto = new CardDto(
                        card.getCardId(),
                        card.getName(),
                        card.getPrimaryType(),
                        card.getSecondaryType(),
                        card.getGrade(),
                        card.getHp(),
                        card.getCardSet(),
                        category(card),
                        null
                );
            }

            dtos.add(new UserCardDto(
                    userCard.getUserCardId(),
                    userCard.getUser().getUserId(),
                    userCard.getCard().getCardId(),
                    userCard.getCollection().getCollectionId(),
                    userCard.getQuantity(),
                    cardDto
            ));
        }
        return dtos;
    }

    public UserCardDto addCardToCollection(Long collectionId, Long cardId, Integer quantity) {
        Card card = cardRepository.findById(cardId).orElse(null);

        if (collectionId == null || card == null || quantity == null) {
            throw new IllegalArgumentException("Missing required card fields");
        }

        CardCollection collection = collectionRepository.findById(collectionId).orElse(null);
        if (collection == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        if (!cardRepository.existsById(cardId)) {
            throw new IllegalArgumentException("Card does not exist");
        }

        User user = collection.getUser();
        Long userId = user.getUserId();
        if (userCardRepository.existsByUser_UserIdAndCard_CardIdAndCollection_CollectionId(userId, cardId, collectionId)) {
            throw new IllegalArgumentException("Card already exists in user's collection");
        }

        UserCard userCard = new UserCard();
        userCard.setUser(user);
        userCard.setCard(card);
        userCard.setCollection(collection);
        userCard.setQuantity(quantity.intValue());
        UserCard saved = userCardRepository.save(userCard);

        CardDto cardDto = null;
        if (card != null) {
            cardDto = new CardDto(
                    card.getCardId(),
                    card.getName(),
                    card.getPrimaryType(),
                    card.getSecondaryType(),
                    card.getGrade(),
                    card.getHp(),
                    card.getCardSet(),
                    category(card),
                    null
            );
        }

        return new UserCardDto(
                saved.getUserCardId(),
                saved.getUser().getUserId(),
                saved.getCard().getCardId(),
                saved.getCollection().getCollectionId(),
                saved.getQuantity(),
                cardDto
        );
    }

    public void removeCardFromCollection(Long collectionId, Long cardId) {
        List<UserCard> userCards = userCardRepository.findByCollection_CollectionId(collectionId);
        for (UserCard userCard : userCards) {
            if (cardId.equals(userCard.getCard().getCardId())) {
                userCardRepository.delete(userCard);
                return;
            }
        }
        throw new IllegalArgumentException("Card not found in collection");
    }

    private String category(Card card) {
        if (card.getPrimaryType() != null) return "Pokemon";
        if (card.getName() != null && card.getName().toLowerCase().contains("energy")) return "Energy";
        return "Trainer";
    }
}
