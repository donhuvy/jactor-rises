package nu.hjemme.client.domain;

/** @author Tor Egil Jacobsen */
public interface BlogEntry extends Persistent<Long>, Entry {

    Blog getBlog();
}
