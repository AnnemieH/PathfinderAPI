package longroad.annemie.PathfinderAPI.Skill;

import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table(name="skill")
public class Skill
{
    @Id
    @Column (name="skill_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short skillID;

    @Column (name="skill_name")
    private String skillName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="attribute")
    private Attribute attribute;

    @Column (name="trained_only")
    private boolean trainedOnly;

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
        setMetadata(new Metadata("/skills", false, toString()));
    }


    // GETTERS
    public short getSkillID()
    {
        return skillID;
    }

    public String getSkillName()
    {
        return skillName;
    }

    public Attribute getAttribute() 
    {
        return attribute;
    }

    public boolean isTrainedOnly()
    {
        return trainedOnly;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setSkillID(short skillID)
    {
        this.skillID = skillID;
    }

    public void setSkillName(String skillName)
    {
        this.skillName = skillName;
    }

    public void setAttribute(Attribute attribute)
    {
        this.attribute = attribute;
    }

    public void setTrainedOnly(boolean trainedOnly)
    {
        this.trainedOnly = trainedOnly;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getSkillName();
    }
}
