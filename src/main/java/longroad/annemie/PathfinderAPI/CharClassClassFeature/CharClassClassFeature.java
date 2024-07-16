package longroad.annemie.PathfinderAPI.CharClassClassFeature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import longroad.annemie.PathfinderAPI.ClassFeature.ClassFeature;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

import java.util.Objects;

@Entity
@Table ( name = "class_class_feature" )
public class CharClassClassFeature
{
    @EmbeddedId
    private CharClassClassFeatureKey id;

    @ManyToOne
    @MapsId ( "classID" )
    @JoinColumn ( name = "class_id" )
    @JsonBackReference( value = "classBuff" )
    private CharClass currClass;

    @ManyToOne
    @MapsId ( "classFeatureID" )
    @JoinColumn ( name = "class_feature_id" )
    private ClassFeature classFeature;

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
        setMetadata(new Metadata("/charClassClassFeatures", true, toString()));
    }

    // GETTERS
    public CharClassClassFeatureKey getId()
    {
        return id;
    }

    public CharClass getCurrClass()
    {
        return currClass;
    }

    public ClassFeature getClassFeature()
    {
        return classFeature;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setId(CharClassClassFeatureKey id)
    {
        this.id = id;
    }

    public void setCurrClass(CharClass currClass)
    {
        this.currClass = currClass;
    }

    public void setClassFeature(ClassFeature classFeature)
    {
        this.classFeature = classFeature;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // CONSTRUCTORS
    public CharClassClassFeature()
    {

    }

    public CharClassClassFeature(CharClassClassFeatureKey id, CharClass currClass, ClassFeature classFeature, short level)
    {
        this.id = id;
        this.currClass = currClass;
        this.classFeature = classFeature;
    }


    // Overridden methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CharClassClassFeature charClassClassFeature = (CharClassClassFeature) o;
        return Objects.equals(id, charClassClassFeature.id) && Objects.equals(currClass, charClassClassFeature.currClass) && Objects.equals(classFeature, charClassClassFeature.classFeature);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, currClass, classFeature);
    }

    @Override
    public String toString()
    {
        return getClassFeature().toString() + " is a class feature of " +
               getCurrClass().toString() + " from level " +
               getId().getLevel();

    }
}
