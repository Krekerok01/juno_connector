<#macro baseTemplateRu title="">
    <!DOCTYPE html>
    <html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
        <meta charset="utf-8">
        <title>Email Template</title>
        <style>
            body {
                font-family: 'Open Sans';
                font-size: 20px;
                line-height: 1.5;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }

            h1 {
                font-size: 24px;
                font-weight: bold;
                text-align: center;
                margin-top: 30px;
                margin-bottom: 20px;
            }

            h2 {
                font-size: 20px;
                font-weight: bold;
                margin-top: 30px;
                margin-bottom: 10px;
            }

            ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            li {
                margin-bottom: 10px;
                font-size: 18px;
            }

            .footer-text {
                margin-top: 50px;
                padding: 20px;
                background-color: #ffffff;
                text-align: left;
                font-size: 14px;
                color: #555555;
            }

            img {
                pointer-events: none;
            }
        </style>
    </head>
    <body>
    <table>
        <tr>
            <td>
                <table width="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
                    <tr>
                        <td style="padding: 20px;">
                            <img src="https://devby.io/blogs/storage/images/19/82/79/13/derived/544314476c1cbcf3ddb7dfb842e98e24.jpg" alt="Logo" style="max-width: 100%; height: auto;">
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 20px;">
                            <#nested>
                            <p>C уважением, команда JUNO-connector</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="footer-text">
                            <p>Контактная информация:</p>
                            <p>Адрес: Минск, Беларусь</p>
                            <p>Телефон: +375-25-333-55-99</p>
                            <p>Email: juno.connector@gmail.com</p>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </body>
    </html>
</#macro>
