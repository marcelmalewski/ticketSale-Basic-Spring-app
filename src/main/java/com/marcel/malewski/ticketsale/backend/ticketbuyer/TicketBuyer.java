package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@Table(name = "ticket_buyer")
@Setter
@ToString
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
   private ZonedDateTime dateOfBirth;
   @OneToOne(cascade = CascadeType.ALL)
   @JoinTable(name = "emp_loyalty_card",
           joinColumns =
                   { @JoinColumn(name = "ticket_buyer_id", referencedColumnName = "id") },
           inverseJoinColumns =
                   { @JoinColumn(name = "loyalty_card_id", referencedColumnName = "id") })
   private LoyaltyCard loyaltyCard;

   public TicketBuyer() {
   }
}
