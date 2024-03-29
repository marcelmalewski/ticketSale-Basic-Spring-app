package com.marcel.malewski.ticketsale.ticket;

import com.marcel.malewski.ticketsale.ticket.dto.TicketResponseDto;
import com.marcel.malewski.ticketsale.ticket.dto.TicketWithValidation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tickets")
public class TicketController {
   private final TicketService ticketService;

   public TicketController(TicketService ticketService) {
      this.ticketService = ticketService;
   }

   @GetMapping
   @Operation(summary = "Get all tickets")
   public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
      List<TicketResponseDto> tickets = this.ticketService.getAllTickets();
      return new ResponseEntity<>(tickets, HttpStatus.OK);
   }

//   @GetMapping(path = "{id}")
//   @Operation(summary = "Get ticket buyer by id")
//   public ResponseEntity<Ticket> getTicketById(@PathVariable("id") long id) {
//      Ticket ticket = this.ticketService.getTicketById(id);
//      return new ResponseEntity<>(ticket, HttpStatus.OK);
//   }

   @PostMapping
   @Operation(summary = "Create new ticket")
   public ResponseEntity<String> postTicket(@Valid @RequestBody TicketWithValidation ticketWithValidation) {
      this.ticketService.postTicket(ticketWithValidation);
      return new ResponseEntity<>("crated", HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update ticket by id")
   public ResponseEntity<String> updateTicket(@PathVariable("id") long id, @Valid @RequestBody TicketWithValidation ticketWithValidation) {
      this.ticketService.putTicketById(id, ticketWithValidation);
      return new ResponseEntity<>("updated", HttpStatus.OK);
   }

//   @PatchMapping(path = "{id}")
//   @Operation(summary = "Update ticket partially by id")
//   public ResponseEntity<Ticket> patchTicket(@PathVariable("id") long id, @Valid @RequestBody Ticket ticket) {
//      Ticket patchedTicket = this.ticketService.patchTicketById(id, ticket);
//      return new ResponseEntity<>(patchedTicket, HttpStatus.OK);
//   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete ticket by id")
   public ResponseEntity<Long> deleteTicketById(@PathVariable("id") long id) {
      this.ticketService.deleteTicketById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}
