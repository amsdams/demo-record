package com.example.demo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ie.corballis.sox.WrongParametersException;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoRecordApplication implements CommandLineRunner {

	@Autowired
	Recorder recorder;

	@Autowired
	SoxRecorder soxRecorder;

	public static void main(String[] args) {
		SpringApplication.run(DemoRecordApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Timer timer = new Timer();
		timer.schedule(new Record(), 0, 5000);

	}

	class Record extends TimerTask {
		public void run() {

			try {
				soxRecorder.record();
			} catch (IOException | WrongParametersException e) {
				log.error("caught e {}", e);
			}

			// soxRecorder.finish();
		}
	}
}
