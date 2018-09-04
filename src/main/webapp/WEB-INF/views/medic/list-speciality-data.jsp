<%-- 
    Document   : list-speciality-data
    Created on : 31/08/2018, 15:35:32
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(specialityList:<c:forEach items="${specialityList}" var="speciality" varStatus="i">
    <c:if test="${speciality.specialityPriv}">
        [speciality:
            {specialityId:${speciality.specialityId}},
            {specialityName:${speciality.specialityName}}        
        ]
        <c:if test="${!i.last}">
            ,
        </c:if>
    </c:if>
</c:forEach>)
