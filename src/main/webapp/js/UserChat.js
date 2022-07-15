Talk.ready.then(function () {
    var me = new Talk.User({
        id: '1',
        name: 'chris123',
        email: 'chris@gmail.com',
        welcomeMessage: 'Hey there! How are you? :-)',
    });
    window.talkSession = new Talk.Session({
        appId: '1',
        me: me,
    });
    var other = new Talk.User({
        id: '3',
        name: 'IronMaidenFangirl',
        email: 'TestEmail2@Test.com',
        welcomeMessage: 'Hey, how can I help?',
    });

    var conversation = talkSession.getOrCreateConversation(
        Talk.oneOnOneId(me, other)
    );
    conversation.setParticipant(me);
    conversation.setParticipant(other);

    var conversation = talkSession.getOrCreateConversation('order_83562938');

    var inbox = talkSession.createInbox({ selected: conversation });
    inbox.mount(document.getElementById('talkjs-container'));
});