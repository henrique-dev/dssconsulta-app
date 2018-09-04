<%-- 
    Document   : work-address-list-data
    Created on : 04/09/2018, 09:08:01
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
(medicWorkAddressList:
<c:forEach items="${medicWorkAddressList}" var="workAddress" varStatus="i">
    [workAddress:
        {workAddressId:${workAddress.medicWorkAddressId}},
        {clinicName:${workAddress.clinicProfile.clinicName}},
        {infoCompl:${workAddress.medicWorkAddressComplement}},
        {specialityName:${workAddress.medicSpeciality.speciality.specialityName}}
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>) 
        