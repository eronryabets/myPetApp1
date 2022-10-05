package com.eronryabets.first_pet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="pet_notes")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Notes {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "text")
    @NonNull
    private String text;

    @Column(name = "tag")
    @NonNull
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NonNull
    private User author;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();;

    @Column(name = "string_date")
    private String stringDate = formatter(date);

    public static String formatter(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String time = date.format(formatter);
        return time;
    }
}
