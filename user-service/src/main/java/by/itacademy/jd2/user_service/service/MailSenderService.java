package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.config.properties.MailProperty;
import by.itacademy.jd2.user_service.service.api.IMailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService implements IMailSenderService {
    private final JavaMailSender mailSender;
    private final MailProperty property;

    @Override
    @Async
    public void send(String code, String email) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(buildEmail(code), true);
            helper.setTo(email);
            helper.setSubject("Email confirmation");
            helper.setFrom(property.getMail());

            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }

    //TODO перенести в шаблон thymeleaf
    @Override
    public String buildEmail(String code) {

        return """
                <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                <html xmlns="http://www.w3.org/1999/xhtml" xmlns:o="urn:schemas-microsoft-com:office:office" style="font-family:arial, 'helvetica neue', helvetica, sans-serif">
                   <head>
                      <meta charset="UTF-8">
                      <meta content="width=device-width, initial-scale=1" name="viewport">
                      <meta name="x-apple-disable-message-reformatting">
                      <meta http-equiv="X-UA-Compatible" content="IE=edge">
                      <meta content="telephone=no" name="format-detection">
                      <title>New message</title>
                      <!--[if (mso 16)]>
                      <style type="text/css">
                         a {text-decoration: none;}
                      </style>
                      <![endif]--><!--[if gte mso 9]>
                      <style>sup { font-size: 100%% !important; }</style>
                      <![endif]--><!--[if gte mso 9]>
                      <xml>
                         <o:OfficeDocumentSettings>
                            <o:AllowPNG></o:AllowPNG>
                            <o:PixelsPerInch>96</o:PixelsPerInch>
                         </o:OfficeDocumentSettings>
                      </xml>
                      <![endif]--><!--[if !mso]><!-- -->
                      <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
                      <!--<![endif]-->
                      <style type="text/css">
                         #outlook a {
                         padding:0;
                         }
                         .es-button {
                         mso-style-priority:100!important;
                         text-decoration:none!important;
                         }
                         a[x-apple-data-detectors] {
                         color:inherit!important;
                         text-decoration:none!important;
                         font-size:inherit!important;
                         font-family:inherit!important;
                         font-weight:inherit!important;
                         line-height:inherit!important;
                         }
                         .es-desk-hidden {
                         display:none;
                         float:left;
                         overflow:hidden;
                         width:0;
                         max-height:0;
                         line-height:0;
                         mso-hide:all;
                         }
                         @media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%% } h1 { font-size:30px!important; text-align:center!important } h2 { font-size:24px!important; text-align:center!important } h3 { font-size:20px!important; text-align:center!important } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important; text-align:center!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:24px!important; text-align:center!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:center!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class="gmail-fix"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%%!important } .adapt-img { width:100%%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } .h-auto { height:auto!important } }
                      </style>
                   </head>
                   <body style="width:100%%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%%;-ms-text-size-adjust:100%%;padding:0;Margin:0">
                      <div class="es-wrapper-color" style="background-color:#FFFFFF">
                         <!--[if gte mso 9]>
                         <v:background xmlns:v="urn:schemas-microsoft-com:vml" fill="t">
                            <v:fill type="tile" color="#ffffff"></v:fill>
                         </v:background>
                         <![endif]-->
                         <table class="es-wrapper" width="100%%" cellspacing="0" cellpadding="0" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%%;height:100%%;background-repeat:repeat;background-position:center top;background-color:#FFFFFF">
                            <tr>
                               <td valign="top" style="padding:0;Margin:0">
                                  <table cellpadding="0" cellspacing="0" class="es-header" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%%;background-color:transparent;background-repeat:repeat;background-position:center top">
                                     <tr>
                                        <td align="center" style="padding:0;Margin:0">
                                           <table bgcolor="#fad939" class="es-header-body" align="center" cellpadding="0" cellspacing="0" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:510px">
                                              <tr>
                                                 <td align="left" style="padding:0;Margin:0;padding-left:20px;padding-right:20px">
                                                    <table cellpadding="0" cellspacing="0" width="100%%" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                       <tr>
                                                          <td align="center" valign="top" style="padding:0;Margin:0;width:470px">
                                                             <table cellpadding="0" cellspacing="0" width="100%%" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                <tr>
                                                                   <td align="center" height="40" style="padding:0;Margin:0"></td>
                                                                </tr>
                                                             </table>
                                                          </td>
                                                       </tr>
                                                    </table>
                                                 </td>
                                              </tr>
                                           </table>
                                        </td>
                                     </tr>
                                  </table>
                                  <table class="es-content" cellspacing="0" cellpadding="0" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%%">
                                     <tr>
                                        <td align="center" style="padding:0;Margin:0">
                                           <table class="es-content-body" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:510px" cellspacing="0" cellpadding="0" align="center" bgcolor="#FAD939">
                                              <tr>
                                                 <td align="left" style="padding:0;Margin:0">
                                                    <table width="100%%" cellspacing="0" cellpadding="0" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                       <tr>
                                                          <td class="es-m-p0r" valign="top" align="center" style="padding:0;Margin:0;width:510px">
                                                             <table width="100%%" cellspacing="0" cellpadding="0" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                <tr>
                                                                   <td align="center" style="padding:0;Margin:0;position:relative"><img class="adapt-img" src="https://qtbqvg.stripocdn.email/content/guids/bannerImgGuid/images/image16909982506186213.png" alt title width="510" style="display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic"></td>
                                                                </tr>
                                                             </table>
                                                          </td>
                                                       </tr>
                                                    </table>
                                                 </td>
                                              </tr>
                                           </table>
                                        </td>
                                     </tr>
                                  </table>
                                  <table cellpadding="0" cellspacing="0" class="es-content" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%%">
                                     <tr>
                                        <td align="center" style="padding:0;Margin:0">
                                           <table bgcolor="#ffffff" class="es-content-body" align="center" cellpadding="0" cellspacing="0" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FAD939;border-radius:0 0 50px 50px;width:510px">
                                              <tr>
                                                 <td align="left" style="padding:0;Margin:0;padding-left:20px;padding-right:20px">
                                                    <table cellpadding="0" cellspacing="0" width="100%%" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                       <tr>
                                                          <td align="center" valign="top" style="padding:0;Margin:0;width:470px">
                                                             <table cellpadding="0" cellspacing="0" width="100%%" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                <tr>
                                                                   <td align="center" style="padding:0;Margin:0">
                                                                      <h1 style="Margin:0;line-height:46px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:38px;font-style:normal;font-weight:bold;color:#5d541d">Пожалуйста подтвердите ваш email адрес</h1>
                                                                   </td>
                                                                </tr>
                                                                <tr>
                                                                   <td align="center" style="padding:0;Margin:0;padding-top:40px;padding-bottom:40px">
                                                                      <h3 style="Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:bold;color:#5D541D">Спасибо за регистрации в Task Manager!</h3>
                                                                      <p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:27px;color:#5D541D;font-size:18px"><br></p>
                                                                      <p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:27px;color:#5D541D;font-size:18px">Чтобы завершить регистрацию, пожалуйста введите код активации на сайте.</p>
                                                                   </td>
                                                                </tr>
                                                                <tr>
                                                                   <td align="center" style="padding:0;Margin:0">
                                                                      <!--[if mso]>
                                                                      <a href="" target="_blank" hidden>
                                                                         <v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word" esdevVmlButton href=""
                                                                            style="height:54px; v-text-anchor:middle; width:144px" arcsize="50%%" stroke="f" fillcolor="#660099">
                                                                            <w:anchorlock></w:anchorlock>
                                                                            <center style='color:#ffffff; font-family:Poppins, sans-serif; font-size:20px; font-weight:700; line-height:20px; mso-text-raise:1px'>%s</center>
                                                                         </v:roundrect>
                                                                      </a>
                                                                      <![endif]--><!--[if !mso]><!-- --><span class="msohide es-button-border" style="border-style:solid;border-color:#2CB543;background:#660099;border-width:0px;display:inline-block;border-radius:30px;width:auto;mso-hide:all"><a href="" class="es-button" target="_blank" style="mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;padding:15px 35px 15px 35px;display:inline-block;background:#660099;border-radius:30px;font-family:Poppins, sans-serif;font-weight:bold;font-style:normal;line-height:24px;width:auto;text-align:center;mso-padding-alt:0;mso-border-alt:10px solid #660099">%s</a></span><!--<![endif]-->
                                                                   </td>
                                                                </tr>
                                                             </table>
                                                          </td>
                                                       </tr>
                                                    </table>
                                                 </td>
                                              </tr>
                                              <tr>
                                                 <td align="left" style="Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:40px">
                                                    <table cellpadding="0" cellspacing="0" width="100%%" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                       <tr>
                                                          <td align="left" style="padding:0;Margin:0;width:470px">
                                                             <table cellpadding="0" cellspacing="0" width="100%%" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                <tr>
                                                                   <td align="center" style="padding:0;Margin:0">
                                                                      <p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:21px;color:#5D541D;font-size:14px">Спасибо,<br>команда Task Manager!&nbsp;</p>
                                                                   </td>
                                                                </tr>
                                                             </table>
                                                          </td>
                                                       </tr>
                                                    </table>
                                                 </td>
                                              </tr>
                                           </table>
                                        </td>
                                     </tr>
                                  </table>
                               </td>
                            </tr>
                         </table>
                      </div>
                   </body>
                </html>
                """.formatted(code, code); //TODO (code, code) -> (code)
    }
}
