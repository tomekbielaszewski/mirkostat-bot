<#setting number_format="computer">
**[ #mirkostatbot ]**
Czyli co się działo przez ostatnie 24 godziny na Mirko?

------------------------------------

<#list tagUsageRanking>
Ranking najpopularniejszych tagów:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        [${item.key}](http://www.wykop.pl/tag/${item.key})
        x**${item.value}**
        (${(item.value/totalTagsUsedCounter)?string.percent} wszystkich)
        </@compress>

        <#if item?counter == 10><#break></#if>
    </#items>
</#list>

------------------------------------

<#list mostVotedTagsRanking>
Ranking najbardziej zaplusowanych tagów:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        [${item.key}](http://www.wykop.pl/tag/${item.key})
        - w tagu rozdano **${item.value}** plusów
        </@compress>

        <#if item?counter == 10><#break></#if>
    </#items>
</#list>

------------------------------------

<#list commentsRanking>
Ranking najlepszych komentarzy:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        [(#)](http://www.wykop.pl/wpis/${item.key.entryId}/#comment-${item.key.id})
        **+${item.value}**
        Autor: @${item.key.author}
        </@compress>

        <#if item?counter == 10><#break></#if>
    </#items>
</#list>

------------------------------------

<#list imageRanking>
Ranking najlepszych obrazków:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
            <#if Entry.isInstance(item.key)>[(#)](http://www.wykop.pl/wpis/${item.key.id})</#if>
            <#if EntryComment.isInstance(item.key)>[(#)](http://www.wykop.pl/wpis/${item.key.entryId}/#comment-${item.key.id})</#if>
        | [(IMG)](${item.key.embed.url})
        **+${item.value}**
        Wstawił: @${item.key.author}
        </@compress>

        <#if item?counter == 10><#break></#if>
    </#items>
</#list>

------------------------------------
<#assign mostVotedUser=mostVotedUsersRanking?first>

Najbardziej zaplusowany Mirek @${mostVotedUser.key} dostał łącznie **${mostVotedUser.value} plusów** zdobytych we wpisach i komentarzach. Brawo! Atencja musi się zgadzać!

------------------------------------
<#assign mostActiveUser=userActivityRanking?first>

Najaktywniejszy Mirek to @${mostActiveUser.key}! Łączna liczba wykonanych przez niego akcji* to **${mostActiveUser.value}**

! * - suma z liczby napisanych wpisów i komentarzy jak i liczby podarowanych plusów

------------------------------------

<#list userEntryCountRanking>
Najczęsciej piszący użytkownicy:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        @${item.key}:
        **${item.value}** wpisów
        </@compress>

        <#if item?counter == 5><#break></#if>
    </#items>
</#list>

------------------------------------

<#list charactersTypedPerUserRanking>
Najwięcej piszący użytkownicy:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        @${item.key}:
        **${item.value}** znaków
        </@compress>

        <#if item?counter == 5><#break></#if>
    </#items>
</#list>

------------------------------------

<#list userCommentCountRanking>
Najwięcej komentujący użytkownicy:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        @${item.key}:
        **${item.value}** komentarzy
        </@compress>

        <#if item?counter == 5><#break></#if>
    </#items>
</#list>

------------------------------------

<#list genderActivityRanking>
Aktywność niebieskich i różowych pasków:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
            <#if item.key="male">Niebieskiepaski:</#if>
            <#if item.key="female">Różowepaski:</#if>
        x**${item.value}**
        </@compress>

    </#items>
</#list>

------------------------------------

<#list userGroupActivityRanking>
Aktywność grup użytkowników:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
            <#if item.key.name()="ORANGE">Pomarańczki:</#if>
            <#if item.key.name()="GREEN">Zielonki:</#if>
            <#if item.key.name()="RED">Bordo:</#if>
            <#if item.key.name()="SILVER">Srebro:</#if>
            <#if item.key.name()="BLACK">Administracja:</#if>
            <#if item.key.name()="BLUE">Sponsorowani:</#if>
            <#if item.key.name()="DELETED">Emoquit:</#if>
        x**${item.value}**
        </@compress>

    </#items>
</#list>

------------------------------------

<#list userAppRanking>
Mirki ostatnio pisały z takich oto aplikacji:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        ${item.key}:
        x**${item.value}**
        </@compress>

    </#items>
</#list>

------------------------------------

<#list embedTypeUsageRanking>
Mirki dodały:
    <#items as item>
        <@compress single_line=true>
        ${item?counter?string["00"]}.
        **${item.value}**
            <#if item.key.name()="IMAGE">Obrazków</#if>
            <#if item.key.name()="VIDEO">Filmików</#if>
        </@compress>

    </#items>
</#list>

------------------------------------

Przez ostatnie 24 godziny **${userCounter}** Mirków napisało **${totalEntriesCounter}** wpisów i **${totalCommentsCounter}** komentarzy o łącznej długosci **${totalCharactersTypedCounter}** znaki. Użyto przy tym **${totalTagsUsedCounter}** unikatowych tagów i rozdano **${totalVotesCounter}** plusów.
Jednakże **${totalUsersNotTaggingCounter}** Mirków napisało **${untaggedEntriesCounter}** nieotagowanych wpisów... Nieładnie... #tagujtogowno! A może #nietagujebonocna?

------------------------------------

by @Grizwold