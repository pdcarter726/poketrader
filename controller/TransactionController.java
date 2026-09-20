package edu.ncsu.csc440.poketrader.controller;

import edu.ncsu.csc440.poketrader.dto.TransactionDto;
import edu.ncsu.csc440.poketrader.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * GET /api/transactions — all buy/sell transactions for the logged-in user
     * 
     * @author Alex Judd
     */
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getUserTransactions(HttpSession session) {
        final Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            List<TransactionDto> response = transactionService.getUserTransactions(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * POST /api/transactions
     * Body: { "buyerUserId": 2, "cardId": 4, "price": 9.99 }
     * The logged-in user is the seller.
     * 
     * @author Alex Judd
     */
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody Map<String, Object> body,
            HttpSession session) {
        final Long sellerUserId = (Long) session.getAttribute("userId");
        if (sellerUserId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            final Long buyerUserId = ((Number) body.get("buyerUserId")).longValue();
            final Long cardId = ((Number) body.get("cardId")).longValue();
            final BigDecimal price = new BigDecimal(body.get("price").toString());

            TransactionDto response = transactionService.createTransaction(
                    sellerUserId, buyerUserId, cardId, price);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e.getMessage().equals("Missing required transaction fields")
                    || e.getMessage().equals("Seller and buyer must be different users")
                    || e.getMessage().equals("Price must be greater than 0")) {
                return ResponseEntity.badRequest().build();
            } else if (e.getMessage().equals("Seller or buyer does not exist")
                    || e.getMessage().equals("Card does not exist")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
