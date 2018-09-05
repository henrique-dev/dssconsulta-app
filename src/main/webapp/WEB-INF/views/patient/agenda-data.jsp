<%-- 
    Document   : agenda-data
    Created on : 31/08/2018, 15:27:11
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(consultList:<c:forEach items="${consultList}" var="consult" varStatus="i">
    [consult:
        {id:${consult.consultId}},
        {forDate:${consult.consultForDate.time}},
        {consulted:${consult.consultConsulted}},
        {medicName:${consult.medicSpeciality.medicProfile.medicName}},
        {specialityName:${consult.medicSpeciality.speciality.specialityName}},
        {clinicName:${consult.medicWorkAddress.clinicProfile.clinicName}},
        {workAddressInfo:${consult.medicWorkAddress.medicWorkAddressComplement}}
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)
