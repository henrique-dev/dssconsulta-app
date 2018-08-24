<%-- 
    Document   : login
    Created on : 02/08/2018, 15:23:46
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de marcação de consulta</title>
    </head>
    <body style="font-family: arial; background: rgba(255, 0, 0, 0.5); text-align: center;">
        <!--div style="text-align: center; margin-top: 2em; margin-bottom: 2em;">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" width="350px">
        </div-->
        <div style="background: white; border-radius: 10px; zoom: 170%; width: auto; min-width: 10rem; display: inline-block; height: auto;             
             text-align: center; position: relative; overflow: auto; padding: 2em;">
            <h3>Registrar-se</h3>
            <div style="padding-top: 2rem; text-align: left">                                    
                <form id="autenticar" action="Cadastrar" method="post">
                    <text style="display: inline-block; width: 150px; text-align: right;">Nome completo: </text>
                    <input type="text" style="width: 350px;" name="name">
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">CPF: </text>
                    <input type="text" style="width: 150px" name="cpf">                                        
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Email: </text>
                    <input type="email" style="width: 350px" name="email">
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Sexo: </text>                                                            
                    <input type="radio" style="width: 50px" name="genre" value="masculino">                                
                    <text style="display: inline-block; text-align: left;">Masculino </text>
                    <input type="radio" style="width: 50px" name="genre" value="feminino">                    
                    <text style="display: inline-block; text-align: right;">Feminino </text>
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Data de nascimento: </text>
                    <input type="date" style="width: 150px" name="birthDate">
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Altura: </text>
                    <input type="text" style="width: 150px" name="height">
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Tipo sanguineo: </text>
                    <input type="text" style="width: 150px" name="bloodType">
                    <br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Telefone: </text>
                    <input type="number" style="width: 150px" name="telephone">
                    <br><br>                                        
                    <text style="display: inline-block; width: 150px; text-align: right;">Nova senha: </text>
                    <input type="password" style="width: 150px" name="password"><br>
                    <text style="display: inline-block; width: 150px; text-align: right;">Repita a senha: </text>
                    <input type="password" style="width: 150px" name="passwordRe">                    
                </form>                     
            </div>          
            <br>
            <button form="autenticar" type="submit" value="Entrar" style="display: inline-block; width: auto; border-radius: 2px; min-width: 20%;">Cadastrar-se</button>            
        </div>

    </body>
</html>
