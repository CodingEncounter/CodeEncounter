Talk.ready.then(function () {
    var me = new Talk.User({
        id: '123456',
        name: 'Alice',
        welcomeMessage: 'Hey there! How are you? :-)',
    });
    window.talkSession = new Talk.Session({
        appId: 'tOGmRmb6',
        me: me,
    });
    var other = new Talk.User({
        id: '654321',
        name: 'Sebastian',
        welcomeMessage: 'Hey, how can I help?',
    });

    var conversation = talkSession.getOrCreateConversation(
        Talk.oneOnOneId(me, other)
    );
    conversation.setParticipant(me);
    conversation.setParticipant(other);

    var inbox = talkSession.createInbox({ selected: conversation });
    inbox.mount(document.getElementById('talkjs-container'));
});