package com.marcel.malewski.ticketsale.seat.dto;

import com.marcel.malewski.ticketsale.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.seat.Seat;
import com.marcel.malewski.ticketsale.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeatResponseDto {
   private Long id;
   private Integer seatNumber;
   private Boolean isPremium;
   private List<String> ticketsMovieNames;
   private Integer cinemaHallNumber;

   static public List<SeatResponseDto> seatsResponseDtoFrom(List<Seat> seats) {
      return seats.stream()
              .map(SeatResponseDto::from)
              .toList();
   }

   static public SeatResponseDto from(Seat seat) {
         return new SeatResponseDto(
                 seat.getId(),
                 seat.getSeatNumber(),
                 seat.getIsPremium(),
                 ticketsMovieNamesFromTickets(seat.getTickets()),
                 cinemaHallNumberFromCinemaHall(seat.getCinemaHall())
         );
   }

   static private List<String> ticketsMovieNamesFromTickets(List<Ticket> tickets) {
      return tickets.stream()
              .map(Ticket::getMovieName)
              .toList();
   }

   static private Integer cinemaHallNumberFromCinemaHall(CinemaHall cinemaHall) {
      return (cinemaHall != null) ? cinemaHall.getHallNumber() : null;
   }
}
