package work_interview.adobe;

public class AdobeQuestion {

    // TODO. 考察对问题的理解，以及编程背后的一些经验
    // Scan/Filter objects and detect mismatch
    // Journey Stack: contains Action which maybe MessageAction
    // Message Stack: list of Message (journeyId, messageId)
    // 判断Journey列表中是否有不不符合条件的MessageAction
    //
    // public boolean detectMismatch(List<Journey> listJourney, List<Message> listMessage) {
    //     Map<String, Message> mapMessages = new HashMap<>();
    //     for(Message message: listMessage) {
    //         mapMessage.put(message.getMessageId(), message);
    //     }
    //
    //     for(Journey journey:listJourney) {
    //         String journeyId = journey.getId();
    //         for(Action action: journey.getActions()) {
    //             String messageId = action.getId();
    //             if (mapMessages.containsKey(messageId)) {
    //                // check journeyId == mapMessages.get(messageId).getJourneyId();
    //             } else {
    //                // can not find MessageAction in Message Stack
    //             }
    //         }
    //     }
    // }
}
