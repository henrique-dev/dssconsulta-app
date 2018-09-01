<%-- 
    Document   : profile-medic-data
    Created on : 31/08/2018, 20:05:19
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(lista:
[medicProfile:
    {id:${medicProfile.id}},
    {name:${medicProfile.medicName}},
    {expAge:${medicProfile.medicProfileExpAge}},
    {evaluationAvg:${medicProfile.medicProfileEvaluationAvg}},
    {evaluationCount:${medicProfile.medicProfileEvaluationCount}},
    {infoCompl:${medicProfile.medicProfileInfoCompl}},
    {bio:${medicProfile.medicProfileBio}},
    (medicSpecialityList:
    <c:forEach items="${medicProfile.medicSpecialityList}" var="medicSpeciality" varStatus="j">
        [speciality:
            {id:${medicSpeciality.speciality.specialityId}},
            {name:${medicSpeciality.speciality.specialityName}},
            {priv:${medicSpeciality.speciality.specialityPriv}}
        ]
        <c:if test="${!j.last}">
        ,
        </c:if>
    </c:forEach>),
    (medicWorkAddressList:
    <c:forEach items="${medicProfile.medicWorkAddressList}" var="workAddress" varStatus="k">
        [workAddress:
            {id:${workAddress.clinicProfile.id}},
            {name:${workAddress.clinicProfile.clinicName}},
            {specialityId:${workAddress.medicSpeciality.speciality.specialityId}},
            {specialityName:${workAddress.medicSpeciality.speciality.specialityName}}
        ]
        <c:if test="${!k.last}">
        ,
        </c:if>
    </c:forEach>)        
])