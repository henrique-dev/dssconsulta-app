<%-- 
    Document   : list-clinic
    Created on : 26/08/2018, 13:31:29
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
(clinicProfileList:<c:forEach items="${clinicProfileList}" var="clinicProfile" varStatus="i">            
    [clinic:{clinicId:${clinicProfile.id}},
    {clinicName:${clinicProfile.clinicName}},
    {clinicCnpj:${clinicProfile.clinicCnpj}},
    {clinicAddress:${clinicProfile.clinicProfileAddress}},
    
    (telephoneList:<c:forEach items="${clinicProfile.clinicTelephoneList}" var="telephone" varStatus="j">
        [telephone:{${telephone.clinicTelephoneNumber}}
        <c:if test="${!j.last}">
            ,
        </c:if>]
    </c:forEach>)
        
    <c:if test="${!i.last}">
        ,
    </c:if>]
</c:forEach>)