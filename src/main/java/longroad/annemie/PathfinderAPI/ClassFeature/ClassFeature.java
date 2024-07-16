package longroad.annemie.PathfinderAPI.ClassFeature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import longroad.annemie.PathfinderAPI.BuffCategory.BuffCategory;
import longroad.annemie.PathfinderAPI.BuffType.BuffType;
import longroad.annemie.PathfinderAPI.CharClassClassFeature.CharClassClassFeature;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.ClassFeatureBonus.ClassFeatureBonus;
import longroad.annemie.PathfinderAPI.Duration.Duration;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="class_feature")
public class ClassFeature
{
    @Id
    @Column ( name = "class_feature_id", unique = true)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int classFeatureID;

    @Column ( name = "name" )
    private String name;

    @Column ( name = "description" )
    @JsonIgnore
    private String descriptionRaw;

    @Transient
    private String[] description;

    @OneToMany(mappedBy = "classFeature")
    @JsonBackReference( value="buffClasses" )
    private Set<CharClassClassFeature> classes = new HashSet<>();

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "buff_type_id")
    private BuffType buffType;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn ( name = "duration_total" )
    @JsonInclude ( JsonInclude.Include.NON_EMPTY )
    private Duration durationTotal;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn ( name = "duration_increment" )
    @JsonInclude ( JsonInclude.Include.NON_EMPTY )
    private Duration durationIncrement;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn ( name = "duration_recharge" )
    @JsonInclude ( JsonInclude.Include.NON_EMPTY )
    private Duration durationRecharge;

    @ManyToOne
    @JoinColumn ( name = "category" )
    private BuffCategory buffCategory;

    @OneToMany ( mappedBy = "classFeature", orphanRemoval = true )
    private Set <ClassFeatureBonus> bonuses = new HashSet<>();

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    private void init()
    {
        buffDescInit();
        metadataInit();
    }

    // Ensure metadata is initiälised properly
    private void metadataInit()
    {
        setMetadata(new Metadata("/classFeatures", true, toString()));
    }

    // Ensure that buffDesc is set correctly
    public void buffDescInit()
    {
        // If buffDescriptionRaw is non-empty, split it at newline characters
        if (descriptionRaw != null )
        {
            description = descriptionRaw.split("\\\\n");
        }
    }

    // GETTERS

    public int getClassFeatureID()
    {
        return classFeatureID;
    }

    public String getName()
    {
        return name;
    }

    public String getDescriptionRaw()
    {
        return descriptionRaw;
    }

    public String[] getDescription()
    {
        return description;
    }

    public BuffType getBuffType()
    {
        return buffType;
    }

    public Duration getDurationTotal()
    {
        return durationTotal;
    }

    public Duration getDurationIncrement()
    {
        return durationIncrement;
    }

    public Duration getDurationRecharge()
    {
        return durationRecharge;
    }

    public BuffCategory getBuffCategory()
    {
        return buffCategory;
    }

    public Set<ClassFeatureBonus> getBonuses()
    {
        return bonuses;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setClassFeatureID(int classFeatureID)
    {
        this.classFeatureID = classFeatureID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescriptionRaw(String descriptionRaw)
    {
        this.descriptionRaw = descriptionRaw;
    }

    public void setBuffDescriptionRaw(String[] buffDesc)
    {
        String raw = "";
        for ( String line : buffDesc )
        {
            raw += line;
        }

        if (raw != descriptionRaw)
        {
            descriptionRaw = raw;
        }
    }

    public void setDescription(String[] description)
    {
        this.description = description;
        // If buffDesc differs from buffDescriptionRaw, set them equivalent
        String raw = "";
        for ( String line : description)
        {
            raw += line + "\r\n";
        }

        if (raw != descriptionRaw)
        {
            descriptionRaw = raw;
        }
    }

    public void setBuffType(BuffType buffType)
    {
        this.buffType = buffType;
    }

    public void setBuffCategory(BuffCategory buffCategory)
    {
        this.buffCategory = buffCategory;
    }

    public void setBonuses(Set<ClassFeatureBonus> bonuses)
    {
        this.bonuses = bonuses;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getName() + "(" + getBuffType().getShortTypeName() + ")";
    }
}
