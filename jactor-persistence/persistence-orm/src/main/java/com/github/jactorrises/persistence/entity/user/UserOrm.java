package com.github.jactorrises.persistence.entity.user;

import com.github.jactorrises.client.datatype.EmailAddress;
import com.github.jactorrises.client.datatype.UserName;
import com.github.jactorrises.persistence.entity.PersistentOrm;
import com.github.jactorrises.persistence.entity.person.PersonOrm;
import com.github.jactorrises.persistence.client.entity.UserEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = "T_USER")
public class UserOrm extends PersistentOrm implements UserEntity {

    @Embedded @AttributeOverride(name = "userName", column = @Column(name = "USER_NAME", nullable = false)) private UserNameEmbeddable userName;
    @JoinColumn(name = "PERSON_ID") @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) private PersonOrm personEntity;
    @Embedded @AttributeOverride(name = "emailAddress", column = @Column(name = "EMAIL")) private EmailAddressEmbeddable emailAddress;

    public UserOrm() {
    }

    /**
     * @param user is used to create an entity
     */
    private UserOrm(UserOrm user) {
        super(user);
        emailAddress = user.emailAddress;
        personEntity = user.copyPerson();
        userName = user.userName;
    }

    private PersonOrm copyPerson() {
        return personEntity != null ? personEntity.copy() : null;
    }

    public UserOrm copy() {
        return new UserOrm(this);
    }

    @Override public boolean equals(Object o) {
        return o == this || o != null && getClass() == o.getClass() &&
                Objects.equals(userName, ((UserOrm) o).userName) &&
                Objects.equals(personEntity, ((UserOrm) o).personEntity) &&
                Objects.equals(emailAddress, ((UserOrm) o).emailAddress);
    }

    @Override public int hashCode() {
        return hash(userName, personEntity, emailAddress);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(userName)
                .append(personEntity)
                .append(emailAddress)
                .toString();
    }

    public UserName getUserName() {
        return userName != null ? userName.fetchUserName() : null;
    }

    public PersonOrm getPerson() {
        return personEntity;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress != null ? emailAddress.fetchEmailAddress() : null;
    }

    public boolean isUserNameEmailAddress() {
        return userName.isName(emailAddress);
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress != null ? new EmailAddressEmbeddable(emailAddress) : null;
    }

    public void setUserName(UserName userName) {
        this.userName = new UserNameEmbeddable(userName);
    }

    public void setPersonEntity(com.github.jactorrises.persistence.client.entity.PersonEntity personEntity) {
        this.personEntity = (PersonOrm) personEntity;
    }
}