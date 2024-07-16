package longroad.annemie.PathfinderAPI.ClassFeatureBonus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Bonus.Bonus;
import longroad.annemie.PathfinderAPI.ClassFeature.ClassFeature;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table ( name = "class_feature_bonus" )
public class ClassFeatureBonus
{
    @EmbeddedId
    private ClassFeatureBonusKey id;

    @ManyToOne ( cascade = CascadeType.ALL )
    @MapsId ( "classFeatureID" )
    @JoinColumn ( name = "class_feature_id" )
    @JsonIgnore
    private ClassFeature classFeature;

    @OneToOne ( cascade = CascadeType.ALL )
    @MapsId ( "bonusID" )
    @JoinColumn ( name = "bonus_id" )
    private Bonus bonus;

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    private void init()
    {
        metadataInit();
    }

    // Ensure metadata is initiälised properly
    private void metadataInit()
    {
        setMetadata(new Metadata("/classFeatureBonuses", true, toString()));
    }

    // GETTERS
    public ClassFeatureBonusKey getId()
    {
        return id;
    }

    public ClassFeature getClassFeature()
    {
        return classFeature;
    }

    public Bonus getBonus()
    {
        return bonus;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setId(ClassFeatureBonusKey id)
    {
        this.id = id;
    }

    public void setClassFeature(ClassFeature classFeature)
    {
        this.classFeature = classFeature;
    }

    public void setBonus(Bonus bonus)
    {
        this.bonus = bonus;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getClassFeature().toString() + " grants a " +
               getBonus().toString();
    }
}
