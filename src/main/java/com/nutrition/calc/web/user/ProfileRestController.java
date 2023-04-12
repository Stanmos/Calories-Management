package com.nutrition.calc.web.user;

import com.nutrition.calc.model.User;
import org.springframework.stereotype.Controller;

import static com.nutrition.calc.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {
    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
