package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "seat")
@ToString
public class Seat {
   @Id
   @SequenceGenerator(
           name = "seat_sequence",
           sequenceName = "seat_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "seat_sequence"
   )
   private Long id;
   @Max(value = 50, message = "Seat number must be less than 50")
   @Positive(message = "Seat number must be positive")
   private Integer seatNumber;
   private Boolean isPremium;
   @ManyToMany(mappedBy = "seats")
   @ToString.Exclude
   private Set<Ticket> tickets;
   @ManyToOne
   @JoinColumn(name = "cinema_hall_id", nullable = false)
   //w zakresie jeden sali nie moze sie powtarzac seatNumber
   private CinemaHall cinemaHall;
}
