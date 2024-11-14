package response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelfAgendaResponse {
    private Long id;
    private String title;
    private String description;
    private Integer tanggal;
    private String bulan;
    private Integer tahun;
}
