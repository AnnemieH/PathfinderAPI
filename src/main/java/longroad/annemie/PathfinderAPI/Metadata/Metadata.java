package longroad.annemie.PathfinderAPI.Metadata;

public class Metadata
{
    // The REST API URL where the data originated
    private String origin;

    // Can REST API users patch or update these data?
    private boolean writable;

    // Store an object's toString value
    private String toString;

    // GETTERS
    public String getOrigin()
    {
        return origin;
    }

    public boolean isWritable()
    {
        return writable;
    }

    public String getToString()
    {
        return toString;
    }

    // SETTERS
    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    public void setWritable(boolean writable)
    {
        this.writable = writable;
    }

    public void setToString(String toString)
    {
        this.toString = toString;
    }

    // CONSTRUCTOR
    public Metadata ( String origin, boolean writable, String toString )
    {
        this.origin = origin;
        this.writable = writable;
        this.toString = toString;
    }
}
