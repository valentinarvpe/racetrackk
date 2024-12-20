<%@ page import="racetrackk.Registration" %>



<div class="fieldcontain ${hasErrors(bean: registrationInstance, field: 'race', 'error')} required">
	<label for="race">
		<g:message code="registration.race.label" default="Race" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="race" name="race.id" from="${racetrackk.Race.list()}" optionKey="id" required="" value="${registrationInstance?.race?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: registrationInstance, field: 'runner', 'error')} required">
	<label for="runner">
		<g:message code="registration.runner.label" default="Runner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="runner" name="runner.id" from="${racetrackk.Runner.list()}" optionKey="id" required="" value="${registrationInstance?.runner?.id}" class="many-to-one"/>

</div>

<g:if test="${!session?.user.admin}">
<div class="fieldcontain ">
	<label for="paid">
		<g:message code="registration.paid.label" default="Paid" />
		
	</label>
	<g:checkBox name="paid" value="${registrationInstance?.paid}" />

</div>
</g:if>

