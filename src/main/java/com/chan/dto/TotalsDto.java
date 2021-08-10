package com.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bronchan
 * @ClassName TotalsDto
 * @date 2021/8/10 20:52
 * @Version 1.0
 * @Description TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalsDto {
    int blogTotals;
    int viewTotals;
    int commentTotals;
    int messageTotals;
}
