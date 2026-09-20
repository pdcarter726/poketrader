package edu.ncsu.csc440.poketrader.controller;

import edu.ncsu.csc440.poketrader.dto.CardDto;
import edu.ncsu.csc440.poketrader.dto.CreateCardDto;
import edu.ncsu.csc440.poketrader.service.CardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * GET /api/cards?name=pikachu&type=Lightning
     * Both params are optional. type can be a Pokemon type, "Trainer", or "Energy".
     * 
     * @author Alex Judd
     */
    @GetMapping
    public ResponseEntity<List<CardDto>> getCards(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type) {
        final List<CardDto> response = cardService.getCards(name, type);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/cards/{id} — returns card with moves list
     *
     * @author Alex Judd
     */
    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable Long id) {
        try {
            final CardDto response = cardService.getCardById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e.getMessage().equals("Card not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * POST /api/cards — Admin only: create a new card.
     * Returns 201 on success, 400 for missing fields, 401 if not logged in, 403 if not admin.
     */
    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CreateCardDto request, HttpSession session) {
        try {
            final CardDto response = cardService.createCard(request, session);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (SecurityException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE /api/cards/{id} — Admin only: delete a card.
     * Returns 204 on success, 401 if not logged in, 403 if not admin, 404 if card not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id, HttpSession session) {
        try {
            cardService.deleteCard(id, session);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (SecurityException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
