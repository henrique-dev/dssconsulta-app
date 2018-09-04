<%-- 
    Document   : list-medic-data
    Created on : 31/08/2018, 16:37:44
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(medicProfileList:
<c:forEach items="${medicProfileList}" var="medic" varStatus="i">
    [medic:
        {id:${medic.id}},
        {name:${medic.medicName}},
        {expAge:${medic.medicProfileExpAge}},
        {evaluationAvg:${medic.medicProfileEvaluationAvg}},
        {evaluationCount:${medic.medicProfileEvaluationCount}},
        (medicSpecialityList:
        <c:forEach items="${medic.medicSpecialityList}" var="medicSpeciality" varStatus="j">
            [speciality:
                {name:${medicSpeciality.speciality.specialityName}},
                {priv:${medicSpeciality.speciality.specialityPriv}}
            ]
            <c:if test="${!j.last}">
            ,
            </c:if>
        </c:forEach>),
        (medicWorkAddressList:
        <c:forEach items="${medic.medicWorkAddressList}" var="workAddress" varStatus="k">
            [workAddress:
                {name:${workAddress.clinicProfile.clinicName}}                
            ]
            <c:if test="${!k.last}">
            ,
            </c:if>
        </c:forEach>)
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)

