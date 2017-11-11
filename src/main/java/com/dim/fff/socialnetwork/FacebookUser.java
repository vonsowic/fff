package com.dim.fff.socialnetwork;

import com.restfb.Facebook;
import lombok.Data;

import java.util.Date;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
@Data
public class FacebookUser extends BasicUser {

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public Date getBirthday() {
        return super.getBirthday();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender);
    }

    @Override
    public void setBirthday(Date birthday) {
        super.setBirthday(birthday);
    }
}
