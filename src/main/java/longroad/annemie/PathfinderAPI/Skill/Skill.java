package longroad.annemie.PathfinderAPI.Skill;

import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import jakarta.persistence.*;

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

    public boolean getTrainedOnly()
    {
        return trainedOnly;
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
}
