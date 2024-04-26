package longroad.annemie.PathfinderAPI.Race;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureSubtype.CreatureSubtype;
import longroad.annemie.PathfinderAPI.CreatureType.CreatureType;
import longroad.annemie.PathfinderAPI.RaceBuff.RaceBuff;
import longroad.annemie.PathfinderAPI.RaceCreatureSubtype.RaceCreatureSubtype;
import longroad.annemie.PathfinderAPI.Size.Size;

import java.util.Set;

@Entity
@Table( name = "race" )
public class Race
{
    @Id
    @Column( name = "race_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short race_id;

    @Column( name = "race_name" )
    private String raceName;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "size" )
    private Size size;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "type" )
    private CreatureType type;

    @OneToMany( cascade = CascadeType.MERGE )
    @JoinColumn( name = "race_id" )
    @JsonInclude( JsonInclude.Include.NON_EMPTY )
    private Set<RaceCreatureSubtype> subtype;

    @Column( name = "speed" )
    short speed;

    @Column ( name = "buff_slots" )
    private String buffSlots;

    @OneToMany ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "race_id" )
    private Set <RaceBuff> buffs;

    // GETTERS

    public short getRace_id()
    {
        return race_id;
    }

    public String getRaceName()
    {
        return raceName;
    }

    public Size getSize()
    {
        return size;
    }

    public CreatureType getType()
    {
        return type;
    }

    public Set<RaceCreatureSubtype> getSubtype()
    {
        return subtype;
    }

    public short getSpeed()
    {
        return speed;
    }

    public String getBuffSlots()
    {
        return buffSlots;
    }

    public Set<RaceBuff> getBuffs()
    {
        return buffs;
    }
}
