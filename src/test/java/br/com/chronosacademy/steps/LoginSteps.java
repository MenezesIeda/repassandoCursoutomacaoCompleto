package br.com.chronosacademy.steps;

import br.com.chronosacademy.core.Driver;
import br.com.chronosacademy.pages.LoginPage;
import br.com.chronosacademy.pages.NewAccountPage;
import enulls.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.util.Map;

public class LoginSteps {
    LoginPage loginPage;
    NewAccountPage newAccountPage;
    String username;

    @Before
    public void iniciaNavegador(Scenario cenario)    {
        new Driver(Browser.CHROME);
        System.out.println(cenario.getName());
    }

    @After
    public void fechaNavegador() {
        Driver.getDriver().quit();
    }

    @Dado("que a modal esteja aberta")
    public void queAModalEstejaAberta() {

        Driver.getDriver().get("https://advantageonlineshopping.com/#/");
        loginPage = new LoginPage();
        loginPage.clickbtnLogin();
        loginPage.visibilityOfBtnFechar();
        loginPage.aguardaLoader();

    }

    @Quando("for realizado o clique fora da modal")
    public void forRealizadoOCliqueForaDaModal() {
        loginPage.clickdivFechaModal();
    }

    @Então("a janela modal deve ser fechada")
    public void aJanelaModalDeveSerFechada() throws Exception {
        try {
            loginPage.visibilityOfBtnFechar();
        } catch (Exception e) {
            throw new Exception("A janela modal nao foi fechada");
        }

    }

    @Quando("for realizado o clique no icone do fechar modal")
    public void forRealizadoOCliqueNoIconeDoFecharModal() {
        loginPage.clickbtnFechaModal();
    }

    @Quando("for realizado o clique no link Create NewAccount")
    public void forRealizadoOCliqueNoLinkCreateNewAccount() {
        loginPage.clicklinkCreateAccount();
    }

    @Então("a pagina Create NewAccount deve ser exibida")
    public void aPaginaCreateNewAccountDeveSerExibida() {
        newAccountPage = new NewAccountPage();
        Assert.assertEquals("CREATE ACCOUNT", newAccountPage.getTextNewAccount());
    }

    @Quando("os campos de login sejam preenchidos da seguinte forma")
    public void osCamposDeLoginSejamPreenchidosDaSeguinteForma(Map<String, String> map) {
        username = map.get("usuario");
        String password = map.get("senha");
        boolean remember = Boolean.parseBoolean(map.get("remember"));
        loginPage.setinpUsername(username);
        loginPage.setinpPassword(password);
        if (remember) {
            loginPage.clickinpRemember();
        }


    }

    @Quando("for realizado o clique no botao sign in")
    public void forRealizadoOCliqueNoBotaoSignIn() {
        loginPage.clickbtnSigIn();
    }

    @Entao("deve ser possivel logar no sistema")
    public void deveSerPossivelLogarNoSistema() {
        Assert.assertEquals(username, loginPage.gettextLogado());
    }


    @Entao("o sistema devera exibir uma mensagem de erro")
    public void oSistemaDeveraExibirUmaMensagemDeErro() {
        Assert.assertEquals("Incorrect user name or password.", loginPage.gettextErroLogin());

    }

    @Entao("o botao sign in deve permanecer desabilitado")
    public void oBotaoSignInDevePermanecerDesabilitado() {
        boolean enable = loginPage.isbtnSigIn();
        Assert.assertFalse(enable);
    }


    }
