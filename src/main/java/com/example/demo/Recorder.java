package com.example.demo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Recorder {
	static final long RECORD_TIME = 60000;
	File wavFile = new File("RecordAudio.wav");
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	TargetDataLine line;


    
	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 8;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		return format;
	}

	void start() {
		try {
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);


			if (!AudioSystem.isLineSupported(info)) {
				log.info("Line not supported");
				System.exit(0);
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start(); 

			log.info("Start capturing...");

			AudioInputStream ais = new AudioInputStream(line);

			log.info("Start recording...");

			AudioSystem.write(ais, fileType, wavFile);

		} catch (LineUnavailableException ex) {
			log.error("caught ex", ex);
		} catch (IOException ioe) {
			log.error("caught ex", ioe);
		}
	}

	void finish() {
		line.stop();
		line.close();
		log.info("Finished");
	}
}
