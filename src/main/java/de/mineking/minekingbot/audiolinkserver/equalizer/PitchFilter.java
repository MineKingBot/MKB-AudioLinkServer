package de.mineking.minekingbot.audiolinkserver.equalizer;

import com.github.natanbc.lavadsp.timescale.TimescalePcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Map;

public class PitchFilter implements Filter {
	private final double pitch;

	public PitchFilter(Map<String, Double> values) {
		this.pitch = values.get("pitch");
	}

	@Override
	public FloatPcmAudioFilter buildFilter(AudioTrack track, AudioDataFormat format, FloatPcmAudioFilter output) {
		return new TimescalePcmAudioFilter(output, format.channelCount, format.sampleRate).setPitchSemiTones(pitch);
	}
}
