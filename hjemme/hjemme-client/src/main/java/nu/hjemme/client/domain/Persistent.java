package nu.hjemme.client.domain;

/**
 * Et domene som lagres til database må ha en identifaktor som identifiserer den i databasen. Denne bestemmer dette.
 * @author Tor Egil Jacobsen
 */
public interface Persistent<T> {
    T getId();
}
