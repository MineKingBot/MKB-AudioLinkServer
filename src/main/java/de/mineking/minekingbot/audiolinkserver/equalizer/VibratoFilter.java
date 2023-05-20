package de.mineking.minekingbot.audiolinkserver.equalizer;

import com.github.natanbc.lavadsp.vibrato.VibratoPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Map;

public class VibratoFilter implements Filter {
	private final double frequency;
	private final double depth;

	public VibratoFilter(Map<String, Double> values) {
		this.frequency = values.get("frequency");
		this.depth = values.get("depth");
	}

	@Override
	public FloatPcmAudioFilter buildFilter(AudioTrack track, AudioDataFormat format, FloatPcmAudioFilter output) {
		return new VibratoPcmAudioFilter(output, format.channelCount, format.sampleRate)
				.setDepth((float) depth)
				.setFrequency((float) frequency);
	}
}
