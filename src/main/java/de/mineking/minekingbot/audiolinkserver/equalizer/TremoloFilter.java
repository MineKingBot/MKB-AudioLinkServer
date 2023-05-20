package de.mineking.minekingbot.audiolinkserver.equalizer;

import com.github.natanbc.lavadsp.tremolo.TremoloPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Map;

public class TremoloFilter implements Filter {
	private final double frequency;
	private final double depth;

	public TremoloFilter(Map<String, Double> values) {
		this.frequency = values.get("frequency");
		this.depth = values.get("depth");
	}

	@Override
	public FloatPcmAudioFilter buildFilter(AudioTrack track, AudioDataFormat format, FloatPcmAudioFilter output) {
		return new TremoloPcmAudioFilter(output, format.channelCount, format.sampleRate)
				.setDepth((float) depth)
				.setFrequency((float) frequency);
	}
}
