package de.mineking.minekingbot.audiolinkserver.equalizer;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.List;

public class Equalizer {
	private final float[] bandMultipliers;

	public Equalizer(List<Float> multipliers) {
		bandMultipliers = new float[multipliers.size()];

		for(int i = 0; i < multipliers.size(); i++) {
			bandMultipliers[i] = multipliers.get(i);
		}
	}

	public FloatPcmAudioFilter buildFilter(AudioTrack track, AudioDataFormat format, FloatPcmAudioFilter output) {
		return new com.sedmelluq.discord.lavaplayer.filter.equalizer.Equalizer(format.channelCount, output, bandMultipliers);
	}
}
