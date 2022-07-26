// // const getAgent = async () => {
// //     const response = await fetch('http://localhost:8081/getUser?userId=1');
// //     console.log(response);
// //     const data = await response.json();
// //     console.log(data);
// //     let agent = new Talk.User({
// //         id: data.id,
// //         name: data.name,
// //         photoUrl: data.dp,
// //         email: data.email,
// //         role: data.role
// //     });
// //     return agent;
// // }
// // const getUser = async () => {
// //     const response = await fetch('http://localhost:8081/getUser?userId=2');
// //     console.log(response);
// //     const data = await response.json();
// //     console.log(data);
// //     let user = new Talk.User({
// //         id: data.id,
// //         name: data.name,
// //         photoUrl: data.dp,
// //         email: data.email,
// //         role: data.role
// //     });
// //     return user;
// // }
//
// (async function() {
//     // await Talk.ready;
//     // let agent = await getAgent();
//     // let user = await getUser();
//     // // const session = new Talk.Session({
//     // //     appId: 'tOGmRmb6',
//     // //     me: user,
//     // });
//     await Talk.ready;
//     const me = new Talk.User({
//         id: '123456',
//         name: 'Alice',
//         email: 'alice@example.com',
//         photoUrl: 'https://demo.talkjs.com/img/alice.jpg',
//         welcomeMessage: 'Hey there! How are you? :-)',
//     });
//     const session = new Talk.Session({
//         appId: 'tOGmRmb6',
//         me: me,
//     });
//     const other = new Talk.User({
//         id: '654321',
//         name: 'Sebastian',
//         email: 'Sebastian@example.com',
//         photoUrl: 'https://demo.talkjs.com/img/sebastian.jpg',
//         welcomeMessage: 'Hey, how can I help?',
//     });
//
//     var conversation = session.getOrCreateConversation(Talk.oneOnOneId(me, other))
//     conversation.setAttributes({
//         welcomeMessages: ["You can start typing your message here and one of our agents will be with you shortly.", "Please do not divulge any of your personal information."]
//     })
//     conversation.setParticipant(me);
//     conversation.setParticipant(other);
//
//     var chatbox = window.talkSession.createChatbox();
//     chatbox.select(conversation);
//     chatbox.mount(document.getElementById('talkjs-container'));
// });
//     // var inbox = session.createInbox(conversation);
//     // inbox.mount(document.getElementById("talkjs-container"));
Talk.ready.then(function () {
    var me = new Talk.User({
        id: '123456',
        name: 'Alice',
        email: 'alice@example.com',
        photoUrl: 'https://demo.talkjs.com/img/alice.jpg',
        welcomeMessage: 'Hey there! How are you? :-)',
    });
    window.talkSession = new Talk.Session({
        appId: 'tOGmRmb6',
        me: me,
    });
    var other = new Talk.User({
        id: '654321',
        name: 'Sebastian',
        email: 'Sebastian@example.com',
        photoUrl: 'https://demo.talkjs.com/img/sebastian.jpg',
        welcomeMessage: 'Hey, how can I help?',
    });

    var conversation = talkSession.getOrCreateConversation(
        Talk.oneOnOneId(me, other)
    );
    conversation.setParticipant(me);
    conversation.setParticipant(other);

    var chatbox = window.talkSession.createChatbox();
    chatbox.select(conversation);
    chatbox.mount(document.getElementById('talkjs-container'));
});