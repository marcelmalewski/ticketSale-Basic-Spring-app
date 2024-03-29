package com.marcel.malewski.ticketsale.ticketbuyer;

import com.marcel.malewski.ticketsale.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.ticket.Ticket;
import com.marcel.malewski.ticketsale.ticketbuyer.agerange.AgeRange;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerWithValidationDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket_buyer")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketBuyer {
   @Id
   @SequenceGenerator(
           name = "ticket_buyer_sequence",
           sequenceName = "ticket_buyer_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "ticket_buyer_sequence"
   )
   private Long id;
   private String firstName;
   private String secondName;
   private String password;
   private Date dateOfBirth;
   private String email;
   private AgeRange ageRange;
   @OneToOne
   @JoinColumn(name = "loyalty_card_id", referencedColumnName = "id")
   private LoyaltyCard loyaltyCard;
   @OneToMany(mappedBy = "ticketBuyer")
   @ToString.Exclude
   private List<Ticket> tickets;

   //ticket buyer dodaj po swojej stronie tylko loyalty card to jest do posta
   static public TicketBuyer from(TicketBuyerWithValidationDto ticketBuyerWithValidationDto, LoyaltyCard loyaltyCard) {
      return new TicketBuyer(
              null,
              ticketBuyerWithValidationDto.getFirstName(),
              ticketBuyerWithValidationDto.getSecondName(),
              ticketBuyerWithValidationDto.getPassword(),
              ticketBuyerWithValidationDto.getDateOfBirth(),
              ticketBuyerWithValidationDto.getEmail(),
              AgeRange.valueOf(ticketBuyerWithValidationDto.getAgeRange()),
              loyaltyCard,
              null
      );
   }
}
