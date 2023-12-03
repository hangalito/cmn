package com.cmn.model

/** Represent each message every student can send to another.
 *
 * @param sender the author of this message
 * @param receiver the other student the message is being sent
 * @param message the message to be sent
 * @param dateTime the time and date the message was sent
 */
data class Message(
    val sender: Student,
    val receiver: Student,
    val message: String,
    val dateTime: String
)
