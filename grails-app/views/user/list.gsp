<g:if test="${session?.user?.admin}">
    <div class="nav">
        <span class="menuButton">
            <a class="home" href="${createLink(uri: '/')}">Home</a>
        </span>
        <span class="menuButton">
            <g:link class="create" action="create">
                <g:message code="default.new.label” args="[entityName]”/>
            </g:link>
        </span>
    </div>
</g:if>