package com.example.demo;

import java.io.IOException;

import org.springframework.stereotype.Service;

import ie.corballis.sox.Sox;
import ie.corballis.sox.WrongParametersException;

@Service
public class SoxRecorder {
	void record() throws IOException, WrongParametersException {
		
		//sox -d -t mp3 film.mp3
		Sox sox = new Sox("/usr/local/bin/sox");
		sox.argument("-d");
		sox.argument("-t", "mp3");
		sox.outputFile("outputFile.mp3");
		sox.execute();
	}
	
	void play() {
		//
	}
}
