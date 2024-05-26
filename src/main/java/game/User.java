package game;

public class User {
    private String name;
    private int score;
    private int heart;
    private static User instance;
    public final int MAX_HEART = 3;
    public final int MIN_SCORE = 0;

    User(String name) {
        this.name = name;
        this.score = 0;
        this.heart = 3;
    }

    /**
     * Get instance.
     */
    public static User getInstance(String name) {
        if (instance == null) {
            instance = new User(name);
        }
        return instance;
    }

    /**
     * Get the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the heart.
     */
    public int getHeart() {
        return heart;
    }

    /**
     * Increase score.
     */
    public void increaseScore() {
        score++;
    }

    /**
     * Decrease heart.
     */
    public void decreaseHeart() {
        heart--;
    }

    /**
     * Increase heart.
     */
    public void increaseHeart() {
        heart++;
    }

    /**
     * Set the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set the heart.
     */
    public void setHeart(int heart) {
        this.heart = heart;
    }
}
