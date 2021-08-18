package com.excmul.mail.application;

import com.excmul.mail.domain.Mail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FakeMailService implements MailService {
    @Transactional
    @Override
    public void send(final Mail mail) {
        System.out.println(mail);

        mail.send();
    }
}
