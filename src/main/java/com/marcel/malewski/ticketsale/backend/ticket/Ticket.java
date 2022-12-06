package com.marcel.malewski.ticketsale.backend.ticket;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.front.dto.TicketWithValidation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ticket")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
   @Id
   @SequenceGenerator(
           name = "ticket_sequence",
           sequenceName = "ticket_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "ticket_sequence"
   )
   private Long id;
   private String movieName;
   private Date showDate;
   //TODO to moglaby by być relacja z obiektem CinemaHall ale nie ma co komplikowac
   private Integer hallNumber;
   @ManyToMany
   @JoinTable(name = "ticket_ticket_buyer",
           joinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id")},
           inverseJoinColumns = { @JoinColumn(name = "ticket_buyer_id", referencedColumnName = "id")}
   )
   @ToString.Exclude
   //sprawdzenie z liczbą osób jest taka sama jak liczba miejsc
   private List<TicketBuyer> ticketBuyers;
   @ManyToMany
   @JoinTable(name = "ticket_seat",
           joinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id")},
           inverseJoinColumns = { @JoinColumn(name = "seat_id", referencedColumnName = "id")}
   )
   @ToString.Exclude
   private List<Seat> seats;

   static public Ticket from(TicketWithValidation ticketWithValidation, List<TicketBuyer> ticketBuyers, List<Seat> seats) {
      return new Ticket(
              ticketWithValidation.getId(),
              ticketWithValidation.getMovieName(),
              ticketWithValidation.getShowDate(),
              ticketWithValidation.getHallNumber(),
              ticketBuyers,
              seats
      );
   }
}
