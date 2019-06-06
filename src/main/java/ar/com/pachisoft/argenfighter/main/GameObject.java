package ar.com.pachisoft.argenfighter.main;

import java.util.Objects;

/**
 * Any object used in the game must be a GameObject, this objects can be added to the game loop
 * If the object is a Renderable it will be rendered when processed by the game loop
 * Also, if the object is a TimeSteppable the timeStep method will be called on the object when processed
 * by the game loop.
 */
public class GameObject {
    /**
     * Last game object id generated
     */
    private static long lastId = 0;

    /**
     * Id of this game object
     */
    private final long id;

    /**
     * Name of the game object
     */
    private final String name;

    /**
     * Creates a new game object
     *
     * @param name Name of the object
     */
    public GameObject(String name) {
        id = lastId++;
        this.name = name;
    }

    /**
     * Gets the game object's ID
     *
     * @return ID of the game object
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the name of the object
     *
     * @return Name of the game object
     */
    public String getName() {
        return name;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o Object to compare
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameObject)) return false;
        GameObject that = (GameObject) o;
        return id == that.id;
    }

    /**
     * Returns the hash code for this object
     *
     * @return Hash code of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}