# test automation software working

In this assignment you will be expected to test the functionality of the staging version of
our website. There are two cases. The first one is straightforward, the second one is
expecting your perspective on the testing.

**tools has been used**
- Java programming language
- Maven Build Tool
- TestNG test framework
- Selenium WebDriver
- Git and GitHub

**Case**

1. Open a Browser (write the generic code such that by changing the parameter the
   browser can be changed.)
2. Navigate to https://staging-web.tiko.io/
3. In the home page there is a postcode field with "Solicita tu Oferta'' button. Verify
   these elements on the page.
4. Fill the postcode field and press the "Solicita tu Oferta'' button.
5. Make sure that the browser is redirected to
   https://staging-web.tiko.io/formulario-oferta/direccion with the postcode already
   filled.
6. Complete the 4-step webform with appropriate data and submit.
7. Make sure that the browser is redirected to
   https://staging-web.tiko.io/formulario-oferta/exito and check the success page
   contains the right information that you had filled in the form. Email and phone
   numbers are especially critical.
8. Save the response.
