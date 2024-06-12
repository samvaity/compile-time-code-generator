package com.service.clientlibrary;

import com.service.clientlibrary.models.AudioTranscription;
import com.service.clientlibrary.models.AudioTranscriptionFormat;
import com.service.clientlibrary.models.AudioTranscriptionOptions;
import com.service.clientlibrary.models.ChatCompletions;
import com.service.clientlibrary.models.ChatCompletionsOptions;
import com.service.clientlibrary.models.ChatRequestMessage;
import com.service.clientlibrary.models.ChatRequestSystemMessage;
import com.service.clientlibrary.models.ChatRequestUserMessage;
import io.clientcore.core.credential.KeyCredential;
import io.clientcore.core.util.binarydata.BinaryData;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OpenAIIntegrationTest {
    private final String OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
    private final String OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");

    OpenAIClient clientLibrary = new OpenAIClientBuilder().endpoint(OPENAI_ENDPOINT).credential(new KeyCredential(OPENAI_KEY)).buildClient();

    @Test
    public void testGetAudioTranscriptionJson() {

        byte[] file = BinaryData.fromFile(Paths.get("C:\\Users\\savaity\\IdeaProjects\\Java\\compile-time-annotation-processor\\compile-time-code-generator\\open-ai-client-library\\src\\test\\resources\\batman.wav")).toBytes();
        AudioTranscriptionOptions audioTranscriptionOptions = new AudioTranscriptionOptions(file);
        audioTranscriptionOptions.setFilename("batman.wav");

        audioTranscriptionOptions.setResponseFormat(AudioTranscriptionFormat.JSON);

        AudioTranscription transcription = clientLibrary.getAudioTranscriptionWithResponse("whisper", audioTranscriptionOptions.getFilename(), audioTranscriptionOptions, null).getValue();
        assertAudioTranscriptionSimpleJson(transcription, BATMAN_TRANSCRIPTION);
    }

    static void assertAudioTranscriptionSimpleJson(AudioTranscription transcription, String expectedText) {
        assertNotNull(transcription);
        assertEquals(expectedText, transcription.getText());
        assertNull(transcription.getDuration());
        assertNull(transcription.getLanguage());
        assertNull(transcription.getTask());
        assertNull(transcription.getSegments());
    }


    protected static final String BATMAN_TRANSCRIPTION =
            "Skills and Abilities. Batman has no inherent superpowers. He relies on his own "
                    + "scientific knowledge, detective skills, and athletic prowess. In the stories, Batman is "
                    + "regarded as one of the world's greatest detectives, if not the world's greatest "
                    + "crime solver. Batman has been repeatedly described as having genius-level intellect, one of"
                    + " the greatest martial artists in the DC universe, and having peak human physical "
                    + "conditioning. He has traveled the world acquiring the skills needed to aid his crusade "
                    + "against crime. His knowledge and expertise in almost every discipline known to man is nearly "
                    + "unparalleled by any other character in the universe. Batman's inexhaustible wealth allows "
                    + "him to access advanced technology. As a proficient scientist, he is able to use and modify "
                    + "those technologies to his advantage. Batman describes Superman as the most dangerous man on "
                    + "earth, able to defeat a team of super-powered extraterrestrials by himself in order to "
                    + "rescue his imprisoned teammates in the first storyline. Superman also considers Batman "
                    + "to be one of the most brilliant minds on the planet. Batman has the ability to function "
                    + "under great physical pain and withstand mind control. He is a master of disguise, multilingual, "
                    + "and an expert in espionage, often gathering information under different identities. "
                    + "Batman's karate, judo, and jujitsu training has made him a master of stealth and escape, "
                    + "allowing him to appear and disappear at will, and to break free from the chains of his past.";


    @Test
    public void testGetChatCompletions() {
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant. You will talk like a pirate."));
        chatMessages.add(new ChatRequestUserMessage("Can you help me?"));
        chatMessages.add(new ChatRequestUserMessage("What's the best way to train a parrot?"));
        ChatCompletions resultChatCompletions = clientLibrary.getChatCompletions("gpt-4-1106-preview", new ChatCompletionsOptions(chatMessages)).getValue();
        System.out.println(resultChatCompletions.getId());
    }
}