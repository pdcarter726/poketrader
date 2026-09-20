package edu.ncsu.csc440.poketrader.controller;

import edu.ncsu.csc440.poketrader.dto.CollectionDto;
import edu.ncsu.csc440.poketrader.dto.UserCardDto;
import edu.ncsu.csc440.poketrader.service.CollectionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    private Long getUserId(HttpSession session) {
        Object attr = session.getAttribute("userId");
        if (attr instanceof Long) return (Long) attr;
        return null;
    }

    /**
     * GET /api/collections — returns all collections for the logged-in user
     *
     * @author Alex Judd
     */
    @GetMapping
    public ResponseEntity<List<CollectionDto>> getUserCollections(HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            List<CollectionDto> response = collectionService.getUserCollections(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * POST /api/collections — create a new named collection
     *
     * @author Alex Judd
     */
    @PostMapping
    public ResponseEntity<CollectionDto> createCollection(@RequestBody CollectionDto dto,
            HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            CollectionDto response = collectionService.createCollection(userId, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            if ("Missing required collection fields".equals(e.getMessage())) {
                return ResponseEntity.badRequest().build();
            } else if ("User does not exist".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/collections/{id}
     *
     * @author Alex Judd
     */
    @GetMapping("/{id}")
    public ResponseEntity<CollectionDto> getCollection(@PathVariable Long id, HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            CollectionDto response = collectionService.getCollectionById(id, userId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            if ("Collection not found".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            } else if ("Collection not owned by user".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * PUT /api/collections/{id} — rename or update description
     *
     * @author Alex Judd
     */
    @PutMapping("/{id}")
    public ResponseEntity<CollectionDto> updateCollection(@PathVariable Long id,
            @RequestBody CollectionDto dto, HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            collectionService.getCollectionById(id, userId);
            CollectionDto response = collectionService.updateCollection(id, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            if ("Collection not found".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            } else if ("Collection not owned by user".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * DELETE /api/collections/{id}
     *
     * @author Alex Judd
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id, HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            collectionService.getCollectionById(id, userId);
            collectionService.deleteCollection(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            if ("Collection not found".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            } else if ("Collection not owned by user".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * GET /api/collections/{id}/cards — cards inside this collection
     *
     * @author Alex Judd
     */
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<UserCardDto>> getCollectionCards(@PathVariable Long id, HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            collectionService.getCollectionById(id, userId);
            List<UserCardDto> response = collectionService.getCardsInCollection(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            if ("Collection not found".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            } else if ("Collection not owned by user".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * POST /api/collections/{id}/cards
     * Body: { "cardId": 4, "quantity": 1 }
     *
     * @author Alex Judd
     */
    @PostMapping("/{id}/cards")
    public ResponseEntity<UserCardDto> addCard(@PathVariable Long id,
            @RequestBody Map<String, Long> body, HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        final Long cardId = body.get("cardId");
        try {
            collectionService.getCollectionById(id, userId);
            final Long qty = body.get("quantity");
            if (qty == null) return ResponseEntity.badRequest().build();
            final Integer cardQuantity = qty.intValue();
            final UserCardDto response = collectionService.addCardToCollection(id, cardId, cardQuantity);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            if ("Missing required card fields".equals(e.getMessage())) {
                return ResponseEntity.badRequest().build();
            } else if ("Collection not found".equals(e.getMessage())
                    || "Card does not exist".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            } else if ("Collection not owned by user".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } else if ("Card already exists in user's collection".equals(e.getMessage())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * DELETE /api/collections/{id}/cards/{cardId}
     *
     * @author Alex Judd
     */
    @DeleteMapping("/{id}/cards/{cardId}")
    public ResponseEntity<Void> removeCard(@PathVariable Long id,
            @PathVariable Long cardId, HttpSession session) {
        Long userId = getUserId(session);
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            collectionService.getCollectionById(id, userId);
            collectionService.removeCardFromCollection(id, cardId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            if ("Card not found in collection".equals(e.getMessage())
                    || "Collection not found".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            } else if ("Collection not owned by user".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
