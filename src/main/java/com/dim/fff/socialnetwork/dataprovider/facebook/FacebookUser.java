package com.dim.fff.socialnetwork.dataprovider.facebook;

import com.dim.fff.socialnetwork.dataprovider.Gender;
import com.dim.fff.socialnetwork.dataprovider.UserInfo;
import com.restfb.Facebook;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
@Data
public class FacebookUser implements UserInfo<Long> {

    @Facebook("id")
    private Long id;

    @Facebook("gender")
    private Gender gender;

    @Facebook("id")
    private Date birthday;

    public void setGender(String gender) {
        if (Objects.equals(gender, "male")) {
            this.gender = Gender.MALE;
        } else if (Objects.equals(gender, "female")) {
            this.gender = Gender.FEMALE;
        } else {
            this.gender = Gender.OTHER;
        }
    }
}
