package com.cmn.model

/** Represent each chat on the app.
 *
 * @param participants every participant of the chat
 * @param messages all the messages of the chat
 */
data class Chat(
    val participants: List<Student>,
    val messages: List<Message>
)
